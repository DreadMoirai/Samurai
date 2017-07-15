/*
 *       Copyright 2017 Ton Ly (BreadMoirai)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.github.breadmoirai.samurai.modules;

import com.github.breadmoirai.samurai7.core.CommandEvent;
import com.github.breadmoirai.samurai7.core.command.Command;
import com.github.breadmoirai.samurai7.core.command.Key;
import com.github.breadmoirai.samurai7.core.response.Response;
import com.github.breadmoirai.samurai7.modules.admin.Admin;

@Admin
@Key("shutdown")
public class ShutdownCommand extends Command{
    @Override
    public Response execute(CommandEvent event) {
        event.getJDA().shutdown();
        return null;
    }
}
