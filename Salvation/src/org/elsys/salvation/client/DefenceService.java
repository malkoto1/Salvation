package org.elsys.salvation.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("defences")
public interface DefenceService extends RemoteService {

	void saveDefences(FunctionalityManager fm);
	FunctionalityManager getHardDefences(FunctionalityManager fm);
	FunctionalityManager getNetDefences(FunctionalityManager fm);
	FunctionalityManager getSoftDefences(FunctionalityManager fm);
}
