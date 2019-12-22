package com.creditshelf.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface Parser {
	 <T> List<T> read(Class<T> clazz, InputStream stream) throws IOException;
}
