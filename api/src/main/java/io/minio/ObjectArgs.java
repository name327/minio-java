/*
 * MinIO Java SDK for Amazon S3 Compatible Cloud Storage, (C) 2020 MinIO, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.minio;

/** Base argument class holds object name and version ID along with bucket information. */
public abstract class ObjectArgs extends BucketArgs {
  protected String objectName;
  protected String versionId;

  public String object() {
    return objectName;
  }

  public String versionId() {
    return versionId;
  }

  /** Base argument builder class for {@link ObjectArgs}. */
  public abstract static class Builder<B extends Builder<B, A>, A extends ObjectArgs>
      extends BucketArgs.Builder<B, A> {
    private void validateName(String name) {
      if (name == null || name.isEmpty()) {
        throw new IllegalArgumentException("object name must be non-null/non-empty string");
      }
    }

    protected void validate(A args) {
      super.validate(args);
      validateName(args.objectName);
    }

    @SuppressWarnings("unchecked") // Its safe to type cast to B as B is inherited by this class
    public B object(String name) {
      validateName(name);
      operations.add(args -> args.objectName = name);
      return (B) this;
    }

    @SuppressWarnings("unchecked") // Its safe to type cast to B as B is inherited by this class
    public B versionId(String versionId) {
      operations.add(args -> args.versionId = versionId);
      return (B) this;
    }
  }
}