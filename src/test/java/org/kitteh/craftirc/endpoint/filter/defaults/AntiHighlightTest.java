package org.kitteh.craftirc.endpoint.filter.defaults;

import org.junit.Assert;
import org.junit.Test;
import org.kitteh.craftirc.endpoint.Message;
import org.kitteh.craftirc.endpoint.TargetedMessage;
import org.kitteh.craftirc.endpoint.filter.FilterManager;
import org.kitteh.craftirc.util.MapBuilder;
import org.kitteh.craftirc.util.PointyEnd;

import java.util.LinkedList;
import java.util.List;

public class AntiHighlightTest {
    private static final String VAR_KEY = "test";

    @Test
    public void meow() {
        try {
            FilterManager registry = new FilterManager(null, null);
            PointyEnd point = new PointyEnd() {
                @Override
                protected void receiveMessage(TargetedMessage message) {
                    Assert.assertEquals("t`e`s`t", message.getCustomData().get(VAR_KEY));
                }
            };
            List<Object> list = new LinkedList<>();
            list.add(new MapBuilder<>().put("splitter", "`").put("variable", VAR_KEY).put("type", "antihighlight").build());
            registry.loadList(list, point.getLoader());
            point.message(new Message(null, "Meow", new MapBuilder<String, Object>().put(VAR_KEY, "test").build()));
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}