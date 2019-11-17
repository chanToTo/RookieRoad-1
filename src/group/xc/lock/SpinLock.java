/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package group.xc.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁，顾名思义就是当未取到锁是，自我空循环去不断尝试获取锁，直到获取成功为止
 * 也可以设置自旋多少次之后如果还未获取到锁则退出锁尝试(不过在本代码中并未实现这点)
 * 自旋锁的好处是，可以减少上下文的切换次数，减少此类开销，但是会相应的耗费一些CPU资源
 * 对于锁竞争激烈的场景或者长时间都不能获得锁的场景下，不推荐使用自旋锁，或者设定自旋次数
 * 避免长时间空循环占用过多的CPU资源
 */
public class SpinLock {
    private AtomicReference<Thread> slot = new AtomicReference<>();
    public void lock() {
        Thread curr = Thread.currentThread();
        do {
           // spin and try to set lock
        } while (! slot.compareAndSet(null, curr));
    }
    public void unlock() {
        Thread curr = Thread.currentThread();
        //release the lock
        slot.compareAndSet(curr, null);
    }
}
