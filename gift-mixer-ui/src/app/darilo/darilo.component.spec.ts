import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DariloComponent } from './darilo.component';

describe('DariloComponent', () => {
  let component: DariloComponent;
  let fixture: ComponentFixture<DariloComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DariloComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DariloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
