import com.onresolve.scriptrunner.runner.util.UserMessageUtil

def flag = [
  type: "warning", //Other possible options are "info", "success", "error"
  body: "Please Request for retrofit codes if not already done.", 
  closeable: true, 
]
UserMessageUtil.flag(flag)
