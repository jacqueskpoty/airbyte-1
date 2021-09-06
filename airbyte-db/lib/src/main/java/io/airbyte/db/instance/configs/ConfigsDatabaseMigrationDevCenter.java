/*
 * MIT License
 *
 * Copyright (c) 2020 Airbyte
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.airbyte.db.instance.configs;

import io.airbyte.db.Database;
import io.airbyte.db.instance.FlywayDatabaseMigrator;
import io.airbyte.db.instance.development.MigrationDevCenter;
import java.io.IOException;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Helper class for migration development. See README for details.
 */
public class ConfigsDatabaseMigrationDevCenter extends MigrationDevCenter {

  public ConfigsDatabaseMigrationDevCenter() {
    super("configs", "src/main/resources/configs_database/schema_dump.txt");
  }

  @Override
  protected FlywayDatabaseMigrator getMigrator(Database database) {
    return new ConfigsDatabaseMigrator(database, ConfigsDatabaseMigrationDevCenter.class.getSimpleName());
  }

  @Override
  protected Database getDatabase(PostgreSQLContainer<?> container) throws IOException {
    return new ConfigsDatabaseInstance(container.getUsername(), container.getPassword(), container.getJdbcUrl()).getAndInitialize();
  }

}
