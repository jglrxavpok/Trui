package org.jglrxavpok.trui.backends.lwjgl3;

import org.jglrxavpok.trui.backends.ImageLoader;
import org.jglrxavpok.trui.render.TruiImage;
import org.jglrxavpok.trui.utils.TruiResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class LWJGLImageLoader implements ImageLoader {
    @Override
    public TruiImage load(TruiResource from) {
        ByteArrayInputStream input = new ByteArrayInputStream(from.getRawData());
        try {
            BufferedImage image = ImageIO.read(input);
            input.close();
            return new TruiImage() {
                @Override
                public int getWidth() {
                    return image.getWidth();
                }

                @Override
                public int getHeight() {
                    return image.getHeight();
                }

                @Override
                public byte[] getRawData() {
                    return from.getRawData();
                }

                @Override
                public String getID() {
                    return from.getID();
                }
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TruiImage() {
            @Override
            public int getWidth() {
                return 0;
            }

            @Override
            public int getHeight() {
                return 0;
            }

            @Override
            public byte[] getRawData() {
                return new byte[0];
            }

            @Override
            public String getID() {
                return from.getID();
            }
        };
    }
}
