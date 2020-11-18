import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PismoComponent } from './pismo.component';

describe('DariloComponent', () => {
  let component: PismoComponent;
  let fixture: ComponentFixture<PismoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PismoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PismoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
