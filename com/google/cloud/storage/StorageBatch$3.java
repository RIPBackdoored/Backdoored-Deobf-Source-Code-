package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.*;
import com.google.api.services.storage.model.*;
import com.google.api.client.googleapis.json.*;

class StorageBatch$3 implements RpcBatch.Callback<StorageObject> {
    final /* synthetic */ StorageBatchResult val$result;
    final /* synthetic */ StorageOptions val$serviceOptions;
    final /* synthetic */ StorageBatch this$0;
    
    StorageBatch$3(final StorageBatch this$0, final StorageBatchResult val$result, final StorageOptions val$serviceOptions) {
        this.this$0 = this$0;
        this.val$result = val$result;
        this.val$serviceOptions = val$serviceOptions;
        super();
    }
    
    @Override
    public void onSuccess(final StorageObject response) {
        this.val$result.success((response == null) ? null : Blob.fromPb((Storage)this.val$serviceOptions.getService(), response));
    }
    
    @Override
    public void onFailure(final GoogleJsonError googleJsonError) {
        this.val$result.error(new StorageException(googleJsonError));
    }
    
    @Override
    public /* bridge */ void onSuccess(final Object o) {
        this.onSuccess((StorageObject)o);
    }
}