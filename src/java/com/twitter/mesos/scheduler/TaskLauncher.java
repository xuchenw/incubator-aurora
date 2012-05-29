package com.twitter.mesos.scheduler;

import com.google.common.base.Optional;

import org.apache.mesos.Protos.Offer;
import org.apache.mesos.Protos.TaskInfo;
import org.apache.mesos.Protos.TaskStatus;

/**
 * A receiver of resource offers and task status updates.
 */
public interface TaskLauncher {

  /**
   * Grants a resource offer to the task launcher, which will be passed to any subsequent task
   * launchers if this one does not accept.
   *
   * @param offer The resource offer.
   * @return A task, absent if the launcher chooses not to accept the offer.
   */
  Optional<TaskInfo> createTask(Offer offer);

  /**
   * Informs the launcher that a status update has been received for a task.  If the task is not
   * associated with the launcher, it should return {@code false} so that another launcher may
   * receive it.
   *
   * @param status The status update.
   * @return {@code true} if the status is relevant to the launcher and should not be delivered to
   * other launchers, {@code false} otherwise.
   */
  boolean statusUpdate(TaskStatus status);
}