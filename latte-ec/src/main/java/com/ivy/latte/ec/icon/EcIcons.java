package com.ivy.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Ivy on 2018/6/8.
 */

public enum EcIcons implements Icon {
    fa_glass('\uf000'),
    fa_music('\uf001');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
