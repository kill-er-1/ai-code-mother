// 等待DOM完全加载后再执行脚本
document.addEventListener('DOMContentLoaded', () => {

    // --- DOM 元素获取 ---
    const addTaskForm = document.getElementById('add-task-form');
    const newTaskInput = document.getElementById('new-task-input');
    const taskList = document.getElementById('task-list');

    // --- 状态管理 ---
    // 尝试从 localStorage 加载任务，如果没有则初始化为空数组
    let tasks = JSON.parse(localStorage.getItem('tasks')) || [];

    // --- 函数定义 ---

    /**
     * 将任务数据保存到 localStorage
     */
    const saveTasks = () => {
        localStorage.setItem('tasks', JSON.stringify(tasks));
    };

    /**
     * 渲染任务列表到页面
     */
    const renderTasks = () => {
        // 清空现有列表
        taskList.innerHTML = '';

        if (tasks.length === 0) {
            taskList.innerHTML = '<p class="no-tasks">太棒了，没有待办事项！</p>';
            return;
        }

        // 遍历任务数组，为每个任务创建 DOM 元素
        tasks.forEach((task, index) => {
            const taskItem = document.createElement('li');
            taskItem.className = 'task-item';
            // 如果任务已完成，添加 'completed' 类
            if (task.completed) {
                taskItem.classList.add('completed');
            }
            // 使用 data-index 属性来追踪任务在数组中的索引
            taskItem.dataset.index = index;

            // 构造任务项的 HTML
            taskItem.innerHTML = `
                <input type="checkbox" class="task-checkbox" ${task.completed ? 'checked' : ''}>
                <span class="task-text">${task.text}</span>
                <button class="delete-btn" title="删除任务">&times;</button>
            `;

            taskList.appendChild(taskItem);
        });
    };

    /**
     * 添加新任务
     * @param {string} text - 任务的文本内容
     */
    const addTask = (text) => {
        if (text) {
            tasks.push({ text: text, completed: false });
            saveTasks();
            renderTasks();
        }
    };

    /**
     * 切换任务的完成状态
     * @param {number} index - 任务在数组中的索引
     */
    const toggleTask = (index) => {
        tasks[index].completed = !tasks[index].completed;
        saveTasks();
        renderTasks();
    };

    /**
     * 删除任务
     * @param {number} index - 任务在数组中的索引
     */
    const deleteTask = (index) => {
        // 从数组中移除指定索引的任务
        tasks.splice(index, 1);
        saveTasks();
        renderTasks();
    };


    // --- 事件监听器 ---

    // 监听表单提交事件来添加新任务
    addTaskForm.addEventListener('submit', (event) => {
        event.preventDefault(); // 阻止表单默认的提交行为
        const taskText = newTaskInput.value.trim(); // 获取并清理输入值
        if (taskText !== '') {
            addTask(taskText);
            newTaskInput.value = ''; // 清空输入框
            newTaskInput.focus(); // 让输入框重新获得焦点
        }
    });

    // 使用事件委托来处理任务列表中的点击事件
    // 这样可以高效地处理动态添加的元素
    taskList.addEventListener('click', (event) => {
        const target = event.target;
        const taskItem = target.closest('.task-item'); // 找到被点击的父级任务项

        if (!taskItem) return;

        const index = parseInt(taskItem.dataset.index, 10);

        // 如果点击的是复选框，则切换任务状态
        if (target.classList.contains('task-checkbox')) {
            toggleTask(index);
        }

        // 如果点击的是删除按钮，则删除任务
        if (target.classList.contains('delete-btn')) {
            deleteTask(index);
        }
    });

    // --- 初始调用 ---
    // 页面加载时立即渲染一次任务列表
    renderTasks();
});