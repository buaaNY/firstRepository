package com.buaa.fangtang_test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;

public class GetRepoAttriThread implements Runnable {

	private String userName, repoName;
	private StringBuilder repoAttriStrB;
	private GitHubClient client;
	private List<MsgShow> msgList;
	public boolean isFinish;

	public GetRepoAttriThread() {
		this.repoAttriStrB = new StringBuilder();
		this.client = new GitHubClient();
		this.msgList = new ArrayList<MsgShow>();
		this.isFinish = false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		client.setCredentials("buaaNY", "woaini52344");
		Repository repo = null;
		try {
			RepositoryService repoService = new RepositoryService(client);
			repo = repoService.getRepository(userName, repoName);
			CommitService commitService = new CommitService(client);
			for (Collection<RepositoryCommit> commitList : commitService
					.pageCommits(repo, 50)) {

				for (RepositoryCommit commit : commitList) {
					MsgShow msg = new MsgShow(commit.getCommit().getAuthor()
							.getName(), commit.getCommit().getAuthor()
							.getDate(), commit.getCommit().getMessage());
					msgList.add(msg);
				}
				break;
			}
			isFinish = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(repoName + "," + userName + " error!\t"
					+ new Date().toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(repoName + "," + userName + " error!\t"
					+ new Date().toString());
		}
	}

	public void threadStart() {
		Thread t = new Thread(this);
		t.start();
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public List<MsgShow> getMsgList() {
		return msgList;
	}

}
