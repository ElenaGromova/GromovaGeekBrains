package client.side;

import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

public interface Callback {
    void callback(Object... args);
}
