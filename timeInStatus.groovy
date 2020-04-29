import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.history.ChangeItemBean

".sfTextML01"

def changeHistoryManager = ComponentAccessor.getChangeHistoryManager()

def inProgressName = "In Progress"

List<Long> rt = [0L]
def changeItems = changeHistoryManager.getChangeItemsForField(issue, "status")
changeItems.reverse().each { ChangeItemBean item ->
    def timeDiff = System.currentTimeMillis() - item.created.getTime()
    if (item.fromString == inProgressName) {
        rt << -timeDiff
    }
    if (item.toString == inProgressName) {
        rt << timeDiff
    }
}

def total = rt.sum() as Long
return (total / 1000) as long ?: 0L