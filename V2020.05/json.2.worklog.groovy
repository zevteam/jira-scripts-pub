import com.atlassian.jira.component.ComponentAccessor
import groovy.json.JsonSlurper

def str = issue.getCustomFieldValue(ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName(".sfWorkLogInfo1"))


def slurper = new groovy.json.JsonSlurper()
def result = slurper.parseText(str)

StringBuilder output = new StringBuilder()
   
result.each{
	output.append("<b>").append(it.key).append(": ").append("</b>").append(it.value).append("</br>")
}

output

