import com.atlassian.core.util.DateUtils
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.issue.fields.CustomField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.* // a
import groovy.json.JsonOutput  
import com.google.gson.*

    
    import java.lang.reflect.Modifier;




@Override
public String toString() 
{
    
      String name;

   // return new ToStringBuilder(this).append("id", name).append("author", location).toString(); 
       return name;
}


class Person {
    String name
    String title
    int age
    String password
    Date dob
    URL favoriteUrl
}

Person person = new Person(name: 'John', title: '', age: 21, password: 'secret',
                            dob: Date.parse('yyyy-MM-dd', '1984-12-15'),
                            favoriteUrl: new URL('http://groovy-lang.org/'))

//// additional code

JiraAuthenticationContext userMgr = ComponentAccessor.getJiraAuthenticationContext();
ApplicationUser currentUserObj = userMgr.getLoggedInUser();

IssueManager issueMgr = ComponentAccessor.getIssueManager();
CustomFieldManager fieldmgr = ComponentAccessor.getCustomFieldManager();
MutableIssue issue = issueMgr.getIssueObject("TST-10") 


/// EOAC



def str

def concStr (String str, String addtxt) {
    str += addtxt;
    return str;
}
 
def worklogManager = ComponentAccessor.getWorklogManager()
def worklogs = worklogManager.getByIssue(issue)
if (worklogs) {
    def worklog = worklogs.last()
    concStr("asd", "123");
   //"\n\n...wlID:${worklog.id}\r\n wlComment: ${worklog.comment}\t byAuthorFName: ${worklog.getAuthorFullName()}\t byUser: ${worklog.author}\t timeSpent: ${DateUtils.getDurationString(worklog.timeSpent)}".toString()
    log.error LocalDateTime.now().toLocalDate();
    log.error LocalDateTime.now().toLocalTime();
    //log.error concStr("asd", "1223").toString();
    
worklog.toString()

  
    
 //  log.error new GsonBuilder().setPrettyPrinting().create().toJson([worklog.id, worklog.author])
    //  log.error groovy.json.JsonOutput.prettyPrint(groovy.json.JsonOutput.toJson(person))
    
Gson gson = new GsonBuilder()
   // .excludeFieldsWithModifiers(Modifier.STATIC)
    .excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT, Modifier.VOLATILE)
    .setPrettyPrinting()
    .create();
    
  //  log.error gson.toJson(worklog)
    
   // issue, id, authorKey, comment, startDate, groupLevel, roleLevelId, timeSpent, updateAuthorKey, created, updated, ProjectRole projectRole
   // groupLevel, roleLevelId, updateAuthorKey, created, updated, ProjectRole projectRole
    def json_out = "{issue.key: '${worklog.issue.key}',\n worklog.id: ${worklog.id},\n worklog.startDate: '${worklog.startDate}',\n worklog.comment: '${worklog.comment}',\n worklog.timeSpent: ${worklog.timeSpent},\n worklog.author: '${worklog.getAuthorFullName()}',\n worklog.created: '${worklog.created}',\n worklog.editor: '${worklog.getUpdateAuthorFullName()}',\n worklog.updated: '${worklog.updated}'}"
 //  groovy.json.JsonOutput.prettyPrint(json_out)
   // log.error json_out
    
    // "${worklog.id}"
    
 /////////////////////////////////////////////////////   System.getProperty("java.ext.dirs")


 //JsonOutput.toJson(worklog)
 //groovy.json.JsonOutput.prettyPrint(groovy.json.JsonOutput.toJson(myObject))


//println "Person Object in JSON : " + JsonOutput.toJson(worklog)
////println "JSON Pretty Print"
//println "-----------------"
// prettyPrint requires a String and NOT an Object
//println JsonOutput.prettyPrint(JsonOutput.toJson(worklog))

}
else {
 "No worklog defined"
}
