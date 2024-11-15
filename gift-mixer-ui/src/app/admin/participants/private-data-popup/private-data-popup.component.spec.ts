import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrivateDataPopupComponent } from './private-data-popup.component';

describe('PrivateDataPopupComponent', () => {
  let component: PrivateDataPopupComponent;
  let fixture: ComponentFixture<PrivateDataPopupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ PrivateDataPopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PrivateDataPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
