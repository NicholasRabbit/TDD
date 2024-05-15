package com.tdd.fixture_patterns;

import java.util.HashMap;
import java.util.Map;

public class InternetRelayChat {

    private Map<String,Client> clients = new HashMap<>();

    public Prompt join(final String nickName, Client user) {
        clients.put(nickName, user);
        return new Prompt() {
            @Override
            public void say(String message) {
                deliverMessage(nickName, message);
            }
        };
    }

    private void deliverMessage(String nickName, String message) {
        for (String key : clients.keySet()) {
            if (!nickName.equals(key)) {
                clients.get(key).onMessage(nickName, message);
            }
        }
    }

}
