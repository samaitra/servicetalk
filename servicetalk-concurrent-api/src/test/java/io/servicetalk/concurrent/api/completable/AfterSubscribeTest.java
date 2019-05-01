/*
 * Copyright © 2018 Apple Inc. and the ServiceTalk project authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.servicetalk.concurrent.api.completable;

import io.servicetalk.concurrent.Cancellable;
import io.servicetalk.concurrent.api.Completable;

import org.junit.Test;

import java.util.function.Consumer;

import static io.servicetalk.concurrent.internal.DeliberateException.DELIBERATE_EXCEPTION;

public class AfterSubscribeTest extends AbstractWhenOnSubscribeTest {

    @Test
    public void testCallbackThrowsError() {
        listener.listen(doSubscribe(Completable.completed(), __ -> {
            throw DELIBERATE_EXCEPTION;
        })).verifyNoEmissions();
    }

    @Override
    protected Completable doSubscribe(Completable completable, Consumer<Cancellable> consumer) {
        return completable.afterOnSubscribe(consumer);
    }
}