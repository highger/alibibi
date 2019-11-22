package com.mgy.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

/**
 * Created by mgy on 2019/11/22
 */
@Slf4j(topic = "spi")
public abstract class Callback {
    public abstract void onSuccess(SendResult result);

    public abstract void onFail(SendResult result);

    public abstract void onException(Exception e);

    private static final Callback DO_NOTHING = new Callback() {

        @Override
        public void onSuccess(SendResult result) {

        }

        @Override
        public void onFail(SendResult result) {

        }

        @Override
        public void onException(Exception e) {

        }
    };

    public static Callback doNothing() {
        return DO_NOTHING;
    }

    public static Callback logOnException() {
        return logOnException(log, "");
    }

    public static Callback logOnException(String message) {
        return logOnException(log, message);
    }

    public static Callback logOnException(@NonNull final Logger logger, @NonNull final String message) {
        return new Callback() {
            @Override
            public void onSuccess(SendResult result) {

            }

            @Override
            public void onFail(SendResult result) {
                logger.error(message + "." + result);
            }

            @Override
            public void onException(Exception e) {
                logger.error(message, e);
            }
        };
    }
}
