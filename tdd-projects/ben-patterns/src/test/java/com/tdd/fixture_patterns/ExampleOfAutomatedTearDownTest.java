package com.tdd.fixture_patterns;

import org.junit.Test;

import static org.easymock.classextension.EasyMock.expect;

public class ExampleOfAutomatedTearDownTest extends AutomatedTeardownTestCase {

    /**
     * To be continued...
     * */
    @Test
    public void messagesAreOnlyToDeliveredToOtherClients() throws Exception {
        String msg = "Hi there!";
        Client alex = (Client) createMock(Client.class);
        Client tom = (Client) createMock(Client.class);
        Client jerry = (Client) createMock(Client.class);

        // Set the expected return with mock object.
        expect(tom.onMessage("alex", msg)).andReturn(true);
        expect(jerry.onMessage("alex", msg)).andReturn(true);

        replayAll();  // a method from the superclass.

        InternetRelayChat irc = new InternetRelayChat();
        irc.join("tom", tom);
        irc.join("jerry", jerry);
        Prompt prompt = irc.join("alex", alex);
        prompt.say(msg);

        // The (@After)tearDown method will be executed automatically in the super class of this class.

    }
}
