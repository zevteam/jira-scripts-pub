import com.atlassian.jira.component.ComponentAccessor
import groovy.json.JsonSlurper


".sfTextCombo"


def str = issue.getCustomFieldValue(ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName(".sfWorkLogInfo1"))


def slurper = new groovy.json.JsonSlurper()
def result = slurper.parseText(str)

StringBuilder output = new StringBuilder()

output.append("<head><style>")
output.append("table#t01 { table-layout: fixed; width: 100%; border: 1px solid black; border-collapse: collapse; }")
output.append("th#th01, td#td01 { border: 1px solid black; border-collapse: collapse; }")
output.append("td#td02 { border: 1px solid black; border-collapse: collapse; font: bold}")
output.append("</style></head>")
output.append("<table id='t01'>")
output.append("<tr>").append("<th id='th01'>").append("Атрибут").append("</th>").append("<th id='th01'>").append("Было").append("</th>")append("<th id='th01'>").append("Стало").append("</th>").append("</tr>")

result.each{
	output.append("<tr>").append("<td id='td02'>").append(it.key).append("</td>").append("<td id='td01'>").append("---").append("</td>")append("<td id='td01'>").append(it.value).append("</td>").append("</tr>")
}

output.append("</table>")

//output

