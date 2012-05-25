package org.elsys.salvation.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DefenceServiceAsync {

	void saveDefences(FunctionalityManager fm, AsyncCallback<Void> callback);

	void getHardDefences(FunctionalityManager fm,
			AsyncCallback<FunctionalityManager> callback);

	void getNetDefences(FunctionalityManager fm,
			AsyncCallback<FunctionalityManager> callback);

	void getSoftDefences(FunctionalityManager fm,
			AsyncCallback<FunctionalityManager> callback);

}
