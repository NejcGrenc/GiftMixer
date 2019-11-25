import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KoncanoComponent } from './koncano.component';

describe('KoncanoComponent', () => {
  let component: KoncanoComponent;
  let fixture: ComponentFixture<KoncanoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KoncanoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KoncanoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
