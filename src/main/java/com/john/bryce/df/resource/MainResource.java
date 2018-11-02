package com.john.bryce.df.resource;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.john.bryce.df.data.DbHandler;
import com.john.bryce.df.data.entity.Chat;
import com.john.bryce.df.data.entity.User;
import com.john.bryce.df.util.fcm.FcmSender;
import com.john.bryce.df.util.fcm.message.FcmChatMessage;
import com.john.bryce.df.util.fcm.message.FcmDrawMessage;
import com.john.bryce.df.util.fcm.message.FcmUserMessage;
import com.john.bryce.df.util.game.GameController;
import com.john.bryce.df.util.json.AnswerRequest;
import com.john.bryce.df.util.json.ErrorDetails;
import com.john.bryce.df.util.json.PushDrawRequest;
import com.john.bryce.df.util.json.QuestionResponse;
import com.john.bryce.df.util.json.UserResponse;
import com.john.bryce.df.util.json.androidcanvas.PointList;

@Path("/")
public final class MainResource {

    private static GameController GAME_CONTROLLER;

    @Path("/getRandomQuestions")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public QuestionResponse getRandomQuestions() {
        final QuestionResponse questionResponse = new QuestionResponse();
        if (GAME_CONTROLLER != null && GAME_CONTROLLER.getQuestions() != null) {
            questionResponse.setQuestions(GAME_CONTROLLER.getQuestions());
            questionResponse.setErrorDetails(ErrorDetails.success());
        } else {
            questionResponse.setErrorDetails(new ErrorDetails(601, "Game hasn't started yet.",
                    "Users must join the game and wait for the game to start before getting questions"));
        }
        return questionResponse;
    }

    @Path("/getDraws")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PointList getDraws() {
        final PointList pointList = GAME_CONTROLLER.getPointList().get(GAME_CONTROLLER.getNumberDrawToSend());
        GAME_CONTROLLER.plusToNumberDraw();
        return pointList;
    }

    @Path("/pushAnswer")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorDetails pushAnswer(AnswerRequest answerRequest) {
        GAME_CONTROLLER.setUserAnswerId(answerRequest.getUserId());
        return ErrorDetails.success();
    }

    @Path("/pushDraw")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorDetails pushDraw(PushDrawRequest pushDrawRequest) {
        GAME_CONTROLLER.setUserDrawerId(pushDrawRequest.getUserId());
        GAME_CONTROLLER.getPointList().add(pushDrawRequest.getPointList());
        final FcmDrawMessage fcmDrawMessage = new FcmDrawMessage();
        //fcmDrawMessage.setPointList(pushDrawRequest.getPointList());
        FcmSender.sendToAllExcept(fcmDrawMessage, pushDrawRequest.getUserId());
        return ErrorDetails.success();
    }

    @Path("/joinOrStartGame")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserResponse joinOrStartGame(User user) {
        if (GAME_CONTROLLER == null) {
            GAME_CONTROLLER = new GameController();
        }

        final UserResponse userResponse = new UserResponse();
        if (GAME_CONTROLLER.incrementUsersCount()) {
            userResponse.setTimeStamp(GAME_CONTROLLER.getTimeForGameToStart());
            if (DbHandler.getInstance().getUserDao().insert(user)) {
                userResponse.setErrorDetails(ErrorDetails.success());
                final FcmUserMessage fcmUserMessage = new FcmUserMessage();
                fcmUserMessage.setUser(user);
                FcmSender.sendToAllExcept(fcmUserMessage, user.getId());
            } else {
                userResponse.setErrorDetails(new ErrorDetails(107, "Database error", "User wasn't inserted to the database"));
            }
        } else {
            userResponse.setTimeStamp(-1);
            userResponse.setErrorDetails(new ErrorDetails(603,
                    "Can't add more users to the game",
                    "The game has already started, either due to the users reaching their maximum allowed limit (" + GameController.MAX_PLAYERS + ") , or due to the timer running out."));
        }
        userResponse.setUsers(DbHandler.getInstance().getUserDao().getAll());
        return userResponse;
    }

    @Path("/pushChat")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorDetails pushChat(Chat chat) {
        if (DbHandler.getInstance().getChatDao().insert(chat)) {
            final FcmChatMessage fcmChatMessage = new FcmChatMessage();
            fcmChatMessage.setChat(chat);
            FcmSender.sendToAllExcept(fcmChatMessage, chat.getUserId());
            return ErrorDetails.success();
        } else {
            return new ErrorDetails(107, "Database error", "Chat wasn't inserted to the database");
        }

    }

}
