import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChainRulesComponent } from './chain-rules.component';

describe('ChainRulesComponent', () => {
  let component: ChainRulesComponent;
  let fixture: ComponentFixture<ChainRulesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChainRulesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChainRulesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
