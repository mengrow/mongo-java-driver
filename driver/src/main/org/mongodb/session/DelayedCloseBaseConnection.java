/*
 * Copyright (c) 2008 - 2013 10gen, Inc. <http://10gen.com>
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

package org.mongodb.session;

import org.mongodb.connection.BaseConnection;
import org.mongodb.connection.ServerAddress;

import static org.mongodb.assertions.Assertions.isTrue;

abstract class DelayedCloseBaseConnection implements BaseConnection {
    private boolean isClosed;

    @Override
    public void close() {
        isClosed = true;
    }

    @Override
    public boolean isClosed() {
        return isClosed;
    }

    @Override
    public ServerAddress getServerAddress() {
        isTrue("open", !isClosed());
        return getWrapped().getServerAddress();
    }

    protected abstract BaseConnection getWrapped();
}