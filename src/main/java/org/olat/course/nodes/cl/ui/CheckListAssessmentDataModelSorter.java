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
package org.olat.course.nodes.cl.ui;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.olat.core.commons.persistence.SortKey;
import org.olat.core.gui.components.form.flexible.elements.MultipleSelectionElement;
import org.olat.core.gui.components.form.flexible.impl.elements.table.SortableFlexiTableModelDelegate;

/**
 * 
 * Initial date: 5 févr. 2017<br>
 * @author srosse, stephane.rosse@frentix.com, http://www.frentix.com
 *
 */
public class CheckListAssessmentDataModelSorter extends SortableFlexiTableModelDelegate<CheckListAssessmentRow> {

	public CheckListAssessmentDataModelSorter(SortKey orderBy, CheckListAssessmentDataModel model, Locale locale) {
		super(orderBy, model, locale);
	}
	
	@Override
	protected void sort(List<CheckListAssessmentRow> rows) {
		int columnIndex = getColumnIndex();
		if(columnIndex >= CheckListAssessmentDataModel.CHECKBOX_OFFSET) {
			int checkBoxIndex = columnIndex - CheckListAssessmentDataModel.CHECKBOX_OFFSET;
			Collections.sort(rows, new CheckBoxComparator(checkBoxIndex));
			
		} else {
			super.sort(rows);
		}
	}
	
	private class CheckBoxComparator implements Comparator<CheckListAssessmentRow> {
		
		private final int checkBoxIndex;
		
		public CheckBoxComparator(int checkBoxIndex) {
			this.checkBoxIndex = checkBoxIndex;
		}
		
		@Override
		public int compare(CheckListAssessmentRow o1, CheckListAssessmentRow o2) {
			if(o1 == null || o2 == null) {
				return compareNullObjects(o1, o2);
			}
			
			boolean c1 = isCheck(o1);
			boolean c2 = isCheck(o2);
			int c = compareBooleans(c1, c2);
			return c;
		}
		
		private boolean isCheck(CheckListAssessmentRow row) {
			if(row.getCheckedEl() != null) {
				//edit mode
				MultipleSelectionElement[] checked = row.getCheckedEl();
				if(checked != null && checkBoxIndex >= 0 && checkBoxIndex < checked.length) {
					return checked[checkBoxIndex].isAtLeastSelected(1);
				}
			}
			
			Boolean[] checked = row.getChecked();
			if(checked != null && checkBoxIndex >= 0 && checkBoxIndex < checked.length
					&& checked[checkBoxIndex] != null) {
				return checked[checkBoxIndex].booleanValue();
			}
			return false;
		}
	}
}