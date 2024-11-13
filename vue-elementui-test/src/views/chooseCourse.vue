<template>
  <div>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column label="序号" width="60" align="center">
        <template slot-scope="scope">
          {{ (pageNum - 1) * pageSize + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column
        prop="course.courseCode"
        label="课程代码"
        width="120"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="course.courseName"
        label="课程名称"
        width="160"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="course.courseType"
        label="性质"
        width="80"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="course.courseCredit"
        label="学分"
        width="80"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="course.courseClass"
        label="班号"
        width="80"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="school.schoolName"
        label="学院"
        width="180"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="teacher.nickname"
        label="老师"
        width="120"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="course.courseTime"
        label="时间"
        width="180"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="course.coursePlace"
        label="地点"
        width="180"
        align="center"
      >
      </el-table-column>
      <el-table-column label="操作" width="180" align="center">
        <template slot-scope="scope">
          <el-button
            @click="chooseCourse(scope.row.course.courseId)"
            type="text"
            size="small"
            >选课</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageNum"
      :page-sizes="[5, 10, 20, 40]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>
  </div>
</template>

<script>
import Qs from "qs";
export default {
  name: "chooseCourse",
  data() {
    return {
      tableData: [],
      pageNum: 1,
      pageSize: 5,
      total: 100,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.$axios
        .get(
          "/course/showThis?pageNum=" +
            this.pageNum +
            "&pageSize=" +
            this.pageSize +
            "&userId=" +
            this.$store.getters.getUser.userId,
          {
            headers: {
              Authorization: this.$store.getters.getToken.token,
            },
          }
        )
        .then((response) => {
          var data = response.data.data;
          if(data==null){
            this.tableData = [];
            this.total = 0;
          }
          console.log("response", response);
          this.tableData = response.data.data.list;
          this.total = response.data.data.total;
        });
    },
    chooseCourse(courseId) {
      this.$axios
        .post(
          "/course/choose",
          {
            userId:this.$store.getters.getUser.userId,
            courseId:courseId
          },
          {
            headers: {
              Authorization: this.$store.getters.getToken.token,
            },
          }
        )
        .then((response) => {
          console.log("response", response);
          this.$message({
            type: "success",
            message: response.data.msg,
          });
          this.getList();
        });
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getList();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getList();
    },
  },
};
</script>

<style>
</style>