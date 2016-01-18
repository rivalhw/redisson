/**
 * Copyright 2014 Nikita Koksharov, Nickolay Borbit
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson;

import io.netty.util.concurrent.Promise;

import java.util.concurrent.Semaphore;

public class RedissonLockEntry implements PubSubEntry<RedissonLockEntry> {

    private int counter;

    private final Semaphore latch;
    private final Promise<RedissonLockEntry> promise;

    public RedissonLockEntry(Promise<RedissonLockEntry> promise) {
        super();
        this.latch = new Semaphore(0);
        this.promise = promise;
    }

    public void aquire() {
        counter++;
    }

    public int release() {
        return --counter;
    }

    public Promise<RedissonLockEntry> getPromise() {
        return promise;
    }

    public Semaphore getLatch() {
        return latch;
    }

}
