package com.so.enums;

public enum VoteType {
    UPVOTE{
        @Override
        public Double getPoint() {
            return 20D;
        }
    }, DOWNVOTE{
        @Override
        public Double getPoint() {
            return -20D;
        }
    };

    public abstract Double getPoint();

}
