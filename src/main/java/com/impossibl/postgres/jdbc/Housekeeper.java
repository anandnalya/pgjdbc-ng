/**
 * Copyright (c) 2013, impossibl.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *  * Neither the name of impossibl.com nor the names of its contributors may
 *    be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.impossibl.postgres.jdbc;

public interface Housekeeper {

  /**
   * Associate a cleanup runnable to be run when a referent is only phantom
   * reference-able.
   *
   * @param referent
   *          Reference to track
   * @param cleanup
   *          Runnable to run when referent is phantom-ed
   * @return Key object to use when calling {@link remove}
   */
  <T> Object add(T referent, Runnable cleanup);

  /**
   * Removes cleanup runnable for the given referent
   *
   * @param referent
   *          Reference to stop tracking
   */
  void remove(Object cleanupKey);

  /**
   * Ensures the cleanup queue is emptied immediately
   */
  void emptyQueue();

  /**
   * ** Only used for unit testing **
   *
   * Checks if a referent has been queued and then processed and removed from
   * the lists
   *
   * @param referent
   *          Referent to check
   * @return
   */
  boolean testCheckCleaned(int referentId);

  /**
   * ** Only used for unit testing **
   *
   * Clears list of references
   */
  void testClear();

}