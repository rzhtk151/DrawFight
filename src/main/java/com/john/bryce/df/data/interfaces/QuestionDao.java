package com.john.bryce.df.data.interfaces;

import java.util.List;

import com.john.bryce.df.data.entity.Question;

public interface QuestionDao extends Dao<Question> {
	
	List<Question> getRandom(int count);
	
}
