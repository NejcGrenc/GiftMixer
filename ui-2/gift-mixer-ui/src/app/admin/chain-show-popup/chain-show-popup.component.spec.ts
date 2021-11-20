import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChainShowPopupComponent } from './chain-show-popup.component';

describe('ChainShowPopupComponent', () => {
  let component: ChainShowPopupComponent;
  let fixture: ComponentFixture<ChainShowPopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChainShowPopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChainShowPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
