// Hiding Menu Button Using JavaScript
// https://scriptrunner.adaptavist.com/latest/jira/fragments/WebResource.html#_hiding_menu_button_using_javascript



(function ($) {
    $(function () {
        /**
         * Binding is required for link such as
         * http://localhost:8080/jira/projects/JRA/issues/JRA-4?filter=allopenissues
         */
        JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (event, $context, reason) { 
            if (reason === JIRA.CONTENT_ADDED_REASON.pageLoad || reason === JIRA.CONTENT_ADDED_REASON.panelRefreshed) {
                hideCloneMenuItem();
            }
        });

        hideCloneMenuItem();


        AJS.messages.info({
           title: 'Operation status',
           body: '<p> Done with operation </p>'
        });

    });

    /**
     * Hide the clone menu
     */
    function hideCloneMenuItem() {
        var projectKey = "FROM"; 
        var issue = JIRA.Issue.getIssueKey();
        if (typeof (issue) !== "undefined") {
            var currentProject = issue.substr(0, issue.indexOf('-')); 
            if (currentProject == projectKey) {
                ////////////// $("a#clone-issue").hide(); 
              //  $("aui-item-link#clone-issue.issueaction-clone-issue").hide(); 
              //  $("a#project-name-val").hide(); 

            }
        }
    }
})(AJS.$);

