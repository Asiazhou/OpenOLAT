/**
 * <a href="http://www.openolat.org">
 * OpenOLAT - Online Learning and Training</a><br>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); <br>
 * you may not use this file except in compliance with the License.<br>
 * You may obtain a copy of the License at the
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">Apache homepage</a>
 * <p>
 * Unless required by applicable law or agreed to in writing,<br>
 * software distributed under the License is distributed on an "AS IS" BASIS, <br>
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. <br>
 * See the License for the specific language governing permissions and <br>
 * limitations under the License.
 * <p>
 * Initial code contributed and copyrighted by<br>
 * frentix GmbH, http://www.frentix.com
 * <p>
 */
package org.olat.ims.qti21.ui;

import java.io.File;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.olat.core.dispatcher.mapper.Mapper;
import org.olat.core.gui.media.FileMediaResource;
import org.olat.core.gui.media.MediaResource;
import org.olat.core.gui.media.NotFoundMediaResource;
import org.olat.core.logging.OLog;
import org.olat.core.logging.Tracing;
import org.olat.core.util.StringHelper;

/**
 * 
 * Initial date: 11.12.2014<br>
 * @author srosse, stephane.rosse@frentix.com, http://www.frentix.com
 *
 */
public class ResourcesMapper implements Mapper {
	
	private static final OLog log = Tracing.createLoggerFor(ResourcesMapper.class);
	
	private final URI assessmentObjectUri;
	
	public ResourcesMapper(URI assessmentObjectUri) {
		this.assessmentObjectUri = assessmentObjectUri;
	}

	@Override
	public MediaResource handle(String relPath, HttpServletRequest request) {
		String filename = null;
		MediaResource resource = null;
		try {
			File root = new File(assessmentObjectUri.getPath());
			String href = request.getParameter("href");
			if(StringHelper.containsNonWhitespace(href)) {
				filename = href;	
			} else if(StringHelper.containsNonWhitespace(relPath)) {
				filename = relPath;
				if(filename.startsWith("/")) {
					filename = filename.substring(1, filename.length());
				}
			}
			
			File file = new File(root.getParentFile(), filename);
			if(file.exists()) {
				resource = new FileMediaResource(file);
			} else {
				resource = new NotFoundMediaResource(href);
			}
		} catch (Exception e) {
			log.error("", e);
			resource = new NotFoundMediaResource(filename);
		}
		return resource;
	}
}