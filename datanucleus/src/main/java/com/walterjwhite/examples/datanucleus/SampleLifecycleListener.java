package com.walterjwhite.examples.datanucleus;

import javax.jdo.PersistenceManager;
import javax.jdo.listener.CreateLifecycleListener;
import javax.jdo.listener.DirtyLifecycleListener;
import javax.jdo.listener.InstanceLifecycleEvent;
import javax.jdo.listener.StoreLifecycleListener;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SampleLifecycleListener
    implements CreateLifecycleListener, StoreLifecycleListener, DirtyLifecycleListener {
  protected final PersistenceManager persistenceManager;

  @Override
  public void postCreate(InstanceLifecycleEvent event) {
    System.out.println(
        "PostCreate:FindById:" + persistenceManager.getObjectById(SampleEntity.class, 1));

    if (AuditEntity.class.equals(event.getSource().getClass())) {
      System.out.println("aborting as this is something we just created.");
      return;
    }

    AuditEntity auditEntity = new AuditEntity();
    auditEntity.setEntityClass(event.getSource().getClass());

    if (SampleEntity.class.isInstance(event.getSource()))
      auditEntity.setEntityId(((SampleEntity) event.getSource()).getId());
    else auditEntity.setEntityId(((AuditEntity) event.getSource()).getId());

    auditEntity.setAction("persist");
    persistenceManager.makePersistent(auditEntity);
  }

  @Override
  public void preDirty(InstanceLifecycleEvent event) {
    System.out.println("preDirty:" + event);
  }

  @Override
  public void postDirty(InstanceLifecycleEvent event) {
    System.out.println("postDirty:" + event);
  }

  @Override
  public void preStore(InstanceLifecycleEvent event) {
    System.out.println("preStore:" + event);
  }

  @Override
  public void postStore(InstanceLifecycleEvent event) {
    System.out.println("postStore:" + event);
  }
}
