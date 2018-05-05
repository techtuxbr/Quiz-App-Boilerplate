package com.londonappbrewery.quizzler;

public class TrueFalse {

    private int mQuestionId;
    private boolean mAnswer;

    public TrueFalse(int questionID, boolean TrueOrFalse){
        mQuestionId = questionID;
        mAnswer = TrueOrFalse;
    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(int questionId) {
        mQuestionId = questionId;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
