
//def command = 'curl --user USERNAME:PASSWORD  "http://JENKINS_URL/job/deploy-dev-test/buildWithParameters?token=MYTOCKEN&ENV=1"'
def command = 'uptime'
def proc = command.execute()
proc.waitFor()              


log.error "$command"
log.error "Process exit code: ${proc.exitValue()}"
log.error "Std Err: ${proc.err.text}"
log.error "Std Out: ${proc.in.text}"
