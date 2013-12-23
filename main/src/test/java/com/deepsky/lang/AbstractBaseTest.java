package com.deepsky.lang;

import com.intellij.mock.MockApplication;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import junit.framework.TestCase;

public abstract class AbstractBaseTest extends TestCase {

    public AbstractBaseTest(){
        ApplicationManager.setApplication(
                new MockApplication(new Disposable() {
                    public void dispose() {
                    }
                }),
                new Disposable() {
                    public void dispose() {
                    }
                }
        );
    }

}
