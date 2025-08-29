package com.example.accounting.infrastructure

import com.example.accounting.domain.department.*
import jooq.tables.Departments.DEPARTMENTS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class DepartmentJDBCRepository(
    private val jooq: DSLContext,
) : DepartmentRepository {
    override fun find(code: DepartmentCode): Department? {
        return jooq.selectFrom(DEPARTMENTS)
            .where(DEPARTMENTS.DEPARTMENT_CODE.eq(code.value))
            .fetchOne()
            ?.let { departmentRecord ->
                Department.reconstruct(
                    DepartmentCode.of(departmentRecord[DEPARTMENTS.DEPARTMENT_CODE]),
                    DepartmentName.of(departmentRecord[DEPARTMENTS.NAME]),
                    departmentRecord[DEPARTMENTS.PARENT_DEPARTMENT_CODE]?.let { DepartmentCode.of(it) }
                )
            }
    }

    override fun list(): List<Department> {
        return jooq.selectFrom(DEPARTMENTS)
            .orderBy(DEPARTMENTS.DEPARTMENT_CODE, DEPARTMENTS.PARENT_DEPARTMENT_CODE)
            .fetch()
            .map { departmentRecord ->
                Department.reconstruct(
                    DepartmentCode.of(departmentRecord[DEPARTMENTS.DEPARTMENT_CODE]),
                    DepartmentName.of(departmentRecord[DEPARTMENTS.NAME]),
                    departmentRecord[DEPARTMENTS.PARENT_DEPARTMENT_CODE]?.let { DepartmentCode.of(it) }
                )
            }
    }

    override fun insert(department: Department) {
        jooq.insertInto(DEPARTMENTS)
            .set(DEPARTMENTS.DEPARTMENT_CODE, department.code.value)
            .set(DEPARTMENTS.NAME, department.name.value)
            .set(DEPARTMENTS.PARENT_DEPARTMENT_CODE, department.parentCode?.value)
            .execute()
    }
}