package org.jglrxavpok.trui.backends;

import java.io.InputStream;

public interface TruiFontFactory {

    TruiFont create(String name, InputStream input, int size);

}
