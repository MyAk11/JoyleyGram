package org.telegram.ui.joyley;

import android.content.Context;
import android.view.View;

import org.telegram.messenger.R;
import org.telegram.messenger.joyley.JoyleyConfig;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalFragment;

import java.util.ArrayList;

public class JoyleySettingsActivity extends UniversalFragment {

    private static final int ID_GHOST_READ = 1;
    private static final int ID_GHOST_TYPING = 2;
    private static final int ID_ALLOW_SCREENSHOTS = 3;
    private static final int ID_HIDE_PHONE = 4;
    private static final int ID_UNLIMITED_PINNED = 5;
    private static final int ID_ABOUT = 6;

    @Override
    protected boolean useSimpleActionBar() {
        return true;
    }

    @Override
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override
    protected CharSequence getTitle() {
        return "JoyleyGram";
    }

    @Override
    protected void fillItems(ArrayList<UItem> items, UniversalAdapter adapter) {
        items.add(UItem.asHeader("Режим призрака"));
        items.add(UItem.asCheck(ID_GHOST_READ, "Не читать сообщения")
                .setChecked(JoyleyConfig.ghostDontSendReadState));
        items.add(UItem.asCheck(ID_GHOST_TYPING, "Скрывать статус набора текста")
                .setChecked(JoyleyConfig.ghostDontSendTyping));
        items.add(UItem.asShadow("Собеседники не узнают, что вы прочитали сообщения или набираете текст."));

        items.add(UItem.asHeader("Безопасность и приватность"));
        items.add(UItem.asCheck(ID_ALLOW_SCREENSHOTS, "Разрешить скриншоты везде")
                .setChecked(JoyleyConfig.allowScreenshots));
        items.add(UItem.asCheck(ID_HIDE_PHONE, "Скрыть номер телефона в меню")
                .setChecked(JoyleyConfig.hidePhoneNumber));
        items.add(UItem.asShadow("Обход защиты FLAG_SECURE: позволяет делать скриншоты в секретных чатах и исчезающих медиа."));

        items.add(UItem.asHeader("Чаты"));
        items.add(UItem.asCheck(ID_UNLIMITED_PINNED, "Больше закреплённых чатов")
                .setChecked(JoyleyConfig.unlimitedPinnedDialogs));
        items.add(UItem.asShadow(null));

        items.add(UItem.asHeader("Информация"));
        items.add(UItem.asButton(ID_ABOUT, "JoyleyGram v1.0", "Официальная сборка форка"));
        items.add(UItem.asShadow("Telegram Fork by Joyley • App ID: 35806758"));
    }

    @Override
    protected void onClick(UItem item, View view, int position, float x, float y) {
        switch (item.id) {
            case ID_GHOST_READ:
                JoyleyConfig.toggleGhostRead();
                if (listView != null && listView.adapter != null) {
                    listView.adapter.update(true);
                }
                break;
            case ID_GHOST_TYPING:
                JoyleyConfig.toggleGhostTyping();
                if (listView != null && listView.adapter != null) {
                    listView.adapter.update(true);
                }
                break;
            case ID_ALLOW_SCREENSHOTS:
                JoyleyConfig.toggleAllowScreenshots();
                if (listView != null && listView.adapter != null) {
                    listView.adapter.update(true);
                }
                break;
            case ID_HIDE_PHONE:
                JoyleyConfig.toggleHidePhoneNumber();
                if (listView != null && listView.adapter != null) {
                    listView.adapter.update(true);
                }
                break;
            case ID_UNLIMITED_PINNED:
                JoyleyConfig.toggleUnlimitedPinned();
                if (listView != null && listView.adapter != null) {
                    listView.adapter.update(true);
                }
                break;
            case ID_ABOUT:
                BulletinFactory.of(this).createSimpleBulletin(R.drawable.msg_settings, "JoyleyGram Fork v1.0").show();
                break;
        }
    }

    @Override
    protected boolean onLongClick(UItem item, View view, int position, float x, float y) {
        return false;
    }
}
