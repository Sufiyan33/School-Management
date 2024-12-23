import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllLeaveComponent } from './get-all-leave.component';

describe('GetAllLeaveComponent', () => {
  let component: GetAllLeaveComponent;
  let fixture: ComponentFixture<GetAllLeaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetAllLeaveComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetAllLeaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
