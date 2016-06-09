package com.liwei.activity;

import io.rong.imkit.fragment.ConversationListFragment;

import io.rong.imlib.model.Conversation;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.liwei.application.MyApplation;
import com.liwei.teachsystem.R;

public class ConversationListActivity extends FragmentActivity{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.conversationlist);
    	MyApplation.setActivity(this);
        enterFragment();
    }

    /**
     * 加载 会话列表 ConversationListFragment
     */
      private void enterFragment() {

          ConversationListFragment fragment = (ConversationListFragment) getSupportFragmentManager().findFragmentById(R.id.conversationlist);

          Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                  .appendPath("conversationlist")
                  .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                  .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                  .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                  .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                  .build();

          fragment.setUri(uri);
      }
}
