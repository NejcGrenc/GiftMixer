import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PismoPopupComponent } from './pismo-popup.component';

describe('PismoPopupComponent', () => {
  let component: PismoPopupComponent;
  let fixture: ComponentFixture<PismoPopupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ PismoPopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PismoPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
