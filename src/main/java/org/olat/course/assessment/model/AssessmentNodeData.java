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
package org.olat.course.assessment.model;

import static org.olat.course.assessment.AssessmentHelper.KEY_ATTEMPTS;
import static org.olat.course.assessment.AssessmentHelper.KEY_DETAILS;
import static org.olat.course.assessment.AssessmentHelper.KEY_IDENTIFYER;
import static org.olat.course.assessment.AssessmentHelper.KEY_INDENT;
import static org.olat.course.assessment.AssessmentHelper.KEY_MAX;
import static org.olat.course.assessment.AssessmentHelper.KEY_MIN;
import static org.olat.course.assessment.AssessmentHelper.KEY_PASSED;
import static org.olat.course.assessment.AssessmentHelper.KEY_SCORE;
import static org.olat.course.assessment.AssessmentHelper.KEY_SCORE_F;
import static org.olat.course.assessment.AssessmentHelper.KEY_SELECTABLE;
import static org.olat.course.assessment.AssessmentHelper.KEY_TITLE_LONG;
import static org.olat.course.assessment.AssessmentHelper.KEY_TITLE_SHORT;
import static org.olat.course.assessment.AssessmentHelper.KEY_TYPE;

import java.util.HashMap;
import java.util.Map;

import org.olat.course.nodes.CourseNode;
import org.olat.modules.assessment.model.AssessmentEntryStatus;

/**
 * 
 * Initial date: 23.10.2015<br>
 * @author srosse, stephane.rosse@frentix.com, http://www.frentix.com
 *
 */
public class AssessmentNodeData {
	
	private int recursionLevel;
	
	private String ident;
	private String type;
	private String shortTitle;
	private String longTitle;
	
	private String details;
	private Integer attempts;
	
	private Float score;
	private String roundedScore;
	
	private Float maxScore;
	private Float minScore;
	
	private Boolean passed;
	private AssessmentEntryStatus assessmentStatus;
	
	private boolean selectable;
	private boolean onyx = false;
	
	public AssessmentNodeData() {
		//
	}
	
	public AssessmentNodeData(Map<String,Object> data) {
		fromMap(data);
	}
	
	public AssessmentNodeData(int indent, CourseNode courseNode) {
		this(indent, courseNode.getIdent(), courseNode.getType(), courseNode.getShortTitle(), courseNode.getLongTitle());
	}

	public AssessmentNodeData(int recursionLevel, String ident, String type, String shortTitle, String longTitle) {
		this.recursionLevel = recursionLevel;
		this.ident = ident;
		this.type = type;
		this.shortTitle = shortTitle;
		this.longTitle = longTitle;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public int getRecursionLevel() {
		return recursionLevel;
	}

	public void setRecursionLevel(int recursionLevel) {
		this.recursionLevel = recursionLevel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getLongTitle() {
		return longTitle;
	}

	public void setLongTitle(String longTitle) {
		this.longTitle = longTitle;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getAttempts() {
		return attempts;
	}

	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getRoundedScore() {
		return roundedScore;
	}

	public void setRoundedScore(String roundedScore) {
		this.roundedScore = roundedScore;
	}

	public Float getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Float maxScore) {
		this.maxScore = maxScore;
	}

	public Float getMinScore() {
		return minScore;
	}

	public void setMinScore(Float minScore) {
		this.minScore = minScore;
	}

	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}

	public AssessmentEntryStatus getAssessmentStatus() {
		return assessmentStatus;
	}

	public void setAssessmentStatus(AssessmentEntryStatus assessmentStatus) {
		this.assessmentStatus = assessmentStatus;
	}

	public boolean isOnyx() {
		return onyx;
	}

	public void setOnyx(boolean onyx) {
		this.onyx = onyx;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public Map<String,Object> toMap() {
		Map<String,Object> nodeData = new HashMap<>();
		
		nodeData.put(KEY_INDENT, new Integer(recursionLevel));
		nodeData.put(KEY_TYPE, getType());
		nodeData.put(KEY_TITLE_SHORT, getShortTitle());
		nodeData.put(KEY_TITLE_LONG, getLongTitle());
		nodeData.put(KEY_IDENTIFYER, getIdent());
		if(details != null) {
			nodeData.put(KEY_DETAILS, details);
		}
		if(attempts != null) {
			nodeData.put(KEY_ATTEMPTS, attempts);
		}
		if(score != null) {
			nodeData.put(KEY_SCORE, roundedScore);
			nodeData.put(KEY_SCORE_F, score);
		}
		if(maxScore != null) {
			nodeData.put(KEY_MAX, maxScore);
		}
		if(minScore != null) {
			nodeData.put(KEY_MIN, minScore);
		}
		if (passed != null) {
			nodeData.put(KEY_PASSED, passed);
		}
		nodeData.put(KEY_SELECTABLE, selectable ? Boolean.TRUE : Boolean.FALSE);
		return nodeData;
	}


	private void fromMap(Map<String,Object> nodeData) {
		recursionLevel = ((Integer)nodeData.get(KEY_INDENT)).intValue();
		
		
	}
	

}
