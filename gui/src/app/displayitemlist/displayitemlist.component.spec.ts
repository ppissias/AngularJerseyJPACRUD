import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayitemlistComponent } from './displayitemlist.component';

describe('DisplayitemlistComponent', () => {
  let component: DisplayitemlistComponent;
  let fixture: ComponentFixture<DisplayitemlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayitemlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayitemlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
