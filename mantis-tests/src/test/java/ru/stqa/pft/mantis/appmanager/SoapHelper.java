package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
  private ApplicationManeger app;
  public SoapHelper(ApplicationManeger app) {
    this.app = app;


  }
  public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
    return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
            .collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    MantisConnectPortType mc = new MantisConnectLocator()
            .getMantisConnectPort(new URL(app.getProperty("web.baseUrl") + "api/soap/mantisconnect.php"));
    return mc;
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories =  mc.mc_project_get_categories(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueData);
    IssueData createdIssueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueId);
    return new Issue().withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                    .withName(createdIssueData.getProject().getName()));
  }







  public String getIssueStatus(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    IssueData issueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
    Issue issue = new Issue().withId(issueData.getId().intValue()).withStatus(issueData.getStatus().getName());
    return issue.getStatus();
  }
}
