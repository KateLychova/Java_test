package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

import static com.google.gson.JsonParser.parseString;

public class TestBase {

  public Boolean isIssueOpen(int issueId) throws IOException {
    String status = getIssueStatus(issueId);
    if (status.equals("Resolved")) {
      return false;
    }
    return true;
  }

  private String getIssueStatus(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json?limit=500")).returnContent().asString();
    JsonElement parsed = parseString(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    JsonElement line = issues.getAsJsonArray().get(0);
    return line.getAsJsonObject().get("state_name").getAsString();
  }





  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }


  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }


}
