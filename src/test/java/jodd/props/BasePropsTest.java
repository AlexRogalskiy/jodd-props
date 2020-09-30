// Copyright (c) 2003-present, Jodd Team (http://jodd.org)
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice,
// this list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

package jodd.props;

import jodd.io.IOUtil;
import jodd.util.ResourcesUtil;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

abstract class BasePropsTest {

	protected InputStream readDataToInputstream(final String fileName) throws IOException {
		String dataFolder = this.getClass().getPackage().getName() + ".data.";
		dataFolder = dataFolder.replace('.', '/');
		return ResourcesUtil.getResourceAsStream(dataFolder + fileName);
	}

	protected File readDataToFile(final String fileName) throws URISyntaxException {
		String dataFolder = this.getClass().getPackage().getName() + ".data.";
		dataFolder = dataFolder.replace('.', '/');

		final URL url = ResourcesUtil.getResourceUrl("/" + dataFolder + fileName);
		return new File(url.toURI());
	}

	protected String readDataFile(final String fileName) throws IOException {
		String dataFolder = this.getClass().getPackage().getName() + ".data.";
		dataFolder = dataFolder.replace('.', '/');

		final InputStream is = ResourcesUtil.getResourceAsStream(dataFolder + fileName);
		final Writer out = new CharArrayWriter();
		String encoding = "UTF-8";
		if (fileName.endsWith(".properties")) {
			encoding = "ISO-8859-1";
		}
		IOUtil.copy(is, out, Charset.forName(encoding));
		IOUtil.close(is);
		return out.toString();
	}

	protected Props loadProps(final Props p, final String fileName) throws IOException {
		String dataFolder = this.getClass().getPackage().getName() + ".data.";
		dataFolder = dataFolder.replace('.', '/');

		final InputStream is = ResourcesUtil.getResourceAsStream(dataFolder + fileName);
		String encoding = "UTF-8";
		if (fileName.endsWith(".properties")) {
			encoding = "ISO-8859-1";
		}
		p.load(is, encoding);
		return p;
	}

	protected Props loadProps(final String fileName) throws IOException {
		final Props p = new Props();
		return loadProps(p, fileName);
	}
}
