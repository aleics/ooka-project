import { AccountType, UserRole } from './user.interface';

export interface Permission {
  accountType: AccountType;
  userRole: UserRole;
}
